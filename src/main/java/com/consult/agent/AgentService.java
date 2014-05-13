package com.consult.agent;

import java.util.List;

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


    public List<Check> checks() {

    }

    public List<Service> services() {

    }

    public List<Member> members() {

    }


    // Return whether it successfully joined or not
    public boolean join(String ipAddress) {

    }

    public void forceLeave(Node node) {

    }

    public boolean registerCheck(String ipAddress, String name, String notes, String script, String interval, String ttl) {

    }

    public boolean deregisterCheck(String checkId) {

    }

    public boolean pass(String checkId) {

    }

    public boolean warn(String checkId) {

    }

    public boolean fail(String checkId) {

    }

    public boolean registerService(String serviceId, String serviceName, List<String> tags, Integer port, String checkScriptPath, String checkScriptInterval, String checkTtl) {

    }

    public boolean deregisterService(String serviceId) {

    }
}
