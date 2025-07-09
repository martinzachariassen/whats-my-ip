package no.zachen.whatsmyip.dto;

public record BasicIpInfo(
    String ip,
    String city,
    String country,
    String isp,
    boolean isVpn
) {}