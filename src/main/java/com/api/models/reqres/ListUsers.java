package com.api.models.reqres;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class ListUsers {

  @JsonProperty("page")
  private String page;
  @JsonProperty("per_page")
  private String perPage;
  @JsonProperty("total")
  private String total;
  @JsonProperty("total_pages")
  private String totalPages;
  @JsonProperty("data")
  private List<UserData> userData;
  @JsonProperty("support")
  private Support support;
}
