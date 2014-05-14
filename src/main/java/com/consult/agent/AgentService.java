package com.consult.agent;

import com.consult.utils.HttpUtils;
import com.consult.utils.JsonUtils;
import com.consult.utils.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgentService {

    private static String ENDPOINT_CHECKS = "/v1/agent/checks";
    private static String ENDPOINT_SERVICES = "/v1/agent/services";
    private static String ENDPOINT_MEMBERS = "/v1/agent/members";
    private static String ENDPOINT_JOIN = "/v1/agent/join/";
    private static String ENDPOINT_FORCELEAVE = "/v1/agent/force-leave/";
    private static String ENDPOINT_REGISTER_SERVICE = "/v1/agent/check/register";
    private static String ENDPOINT_DEREGISTER_SERVICE = "/v1/agent/check/deregister/";
    private static String ENDPOINT_PASS = "/v1/agent/check/pass/";
    private static String ENDPOINT_WARN = "/v1/agent/check/warn/";
    private static String ENDPOINT_FAIL = "/v1/agent/check/fail/";
    private static String ENDPOINT_REGISTER_CHECK = "/v1/agent/service/register";
    private static String ENDPOINT_DEREGISTER_CHECK = "/v1/agent/service/deregister/";

    private String host;

    public AgentService(String host) {
        this.host = host;
    }

    public List<Check> checks() {
        try {
            String json = HttpUtils.get(host + ENDPOINT_CHECKS);
            Map<String,Check> checks = (Map<String,Check>)JsonUtils.fromJson(json, new TypeReference<Map<String,Check>>() {});
            return new ArrayList<Check>(checks.values());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Check>();
        }
    }

    public List<Service> services() {
        try {
            String json = HttpUtils.get(host + ENDPOINT_SERVICES);
            Map<String,Service> services = (Map<String,Service>)JsonUtils.fromJson(json, new TypeReference<Map<String,Service>>() {});
            return new ArrayList<Service>(services.values());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Service>();
        }
    }

    public List<Member> members() {
        return members(false);
    }

    public List<Member> members(boolean wan) {
        try {
            String json = HttpUtils.get(host + ENDPOINT_MEMBERS);
            return (List<Member>)JsonUtils.fromJson(json, new TypeReference<List<Member>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Member>();
        }
     }

    // Return whether it successfully joined or not
    public boolean join(String ipAddress) {
        return join(ipAddress, false);
    }

    public boolean join(String ipAddress, boolean wan) {
        return HttpUtils.noResponseGet(host + ENDPOINT_JOIN + (wan ? "?wan=1" : ""));
    }

    public void forceLeave(Node node) {
        HttpUtils.noResponseGet(host + ENDPOINT_FORCELEAVE + node.name);
    }

    public boolean registerCheckWithScript(String id, String name, String notes, String script) {
        return registerCheck(id, name, notes, script, null, null);
    }

    public boolean registerCheckWithInterval(String id, String name, String notes, String interval) {
        return registerCheck(id, name, notes, null, interval, null);
    }

    public boolean registerCheckWithTtl(String id, String name, String notes, String ttl) {
        return registerCheck(id, name, notes, null, ttl, null);
    }

    private boolean registerCheck(String id, String name, String notes, String scriptPath, String interval, String ttl) {
        Map<String,Object> checkData = new HashMap<String,Object>();

        checkData.put("ID", id);
        checkData.put("Name", name);
        checkData.put("Notes", notes);

        if (!StringUtils.isEmpty(scriptPath)) checkData.put("Script", scriptPath);
        if (!StringUtils.isEmpty(interval)) checkData.put("Interval", interval);
        if (!StringUtils.isEmpty(ttl)) checkData.put("TTL", ttl);

        return HttpUtils.noResponsePut(host + (ENDPOINT_REGISTER_CHECK, JsonUtils.toJson(checkData));
    }

    public boolean deregisterCheck(String id) {
        return HttpUtils.noResponseGet(host + ENDPOINT_DEREGISTER_CHECK + id);
    }

    public boolean pass(String id) {
        return HttpUtils.noResponseGet(host + ENDPOINT_PASS + id);
    }

    public boolean warn(String id) {
        return HttpUtils.noResponseGet(host + ENDPOINT_WARN + id);
    }

    public boolean fail(String id) {
        return HttpUtils.noResponseGet(host + ENDPOINT_FAIL + id);
    }

    public boolean registerServiceWithCheckScript(String id, String name, List<String> tags, Integer port, String checkScriptPath) {
        return registerService(id, name, tags, port, checkScriptPath, null, null);
    }

    public boolean registerServiceWithCheckInterval(String id, String name, List<String> tags, Integer port, String checkInterval) {
        return registerService(id, name, tags, port, null, checkInterval, null);
    }

    public boolean registerServiceWithCheckTtl(String id, String name, List<String> tags, Integer port, String checkTtl) {
        return registerService(id, name, tags, port, null, null, checkTtl);
    }

    private boolean registerService(String id, String name, List<String> tags, Integer port, String checkScriptPath, String checkInterval, String checkTtl) {

        Map<String,Object> serviceData = new HashMap<String,Object>();

        serviceData.put("ID", id);
        serviceData.put("Name", name);
        if (tags != null) serviceData.put("Tags", tags);
        if (port != null) serviceData.put("Port", port);

        if (!StringUtils.areAllEmpty(checkScriptPath, checkInterval, checkTtl)) {
            Map<String,Object> checkData = new HashMap<String,Object>();

            if (!StringUtils.isEmpty(checkScriptPath)) checkData.put("Script", checkScriptPath);
            if (!StringUtils.isEmpty(checkInterval)) checkData.put("Interval", checkInterval);
            if (!StringUtils.isEmpty(checkTtl)) checkData.put("TTL", checkTtl);

            serviceData.put("Check", checkData);
        }

        return HttpUtils.noResponsePut(host + (ENDPOINT_REGISTER_SERVICE, JsonUtils.toJson(serviceData));
    }

    public boolean deregisterService(String id) {
        return HttpUtils.noResponseGet(host + ENDPOINT_DEREGISTER_SERVICE);
    }
}
