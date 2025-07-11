package no.zachen.whatsmyip.dto;

public record IpApiResponse(
    String city,
    String country_name,
    String org,
    Security security
) {

  public record Security(boolean is_vpn) {

  }
}