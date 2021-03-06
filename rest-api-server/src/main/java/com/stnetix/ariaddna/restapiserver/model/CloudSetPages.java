package com.stnetix.ariaddna.restapiserver.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;

/**
 * This page-object shall be used for visual representation of a list (which is inside a CloudSet) of user&#39;s external cloud accounts.
 */
@ApiModel(description = "This page-object shall be used for visual representation of a list (which is inside a CloudSet) of user's external cloud accounts.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-24T10:27:06.657+03:00")

public class CloudSetPages   {
  @JsonProperty("offset")
  private Integer offset = null;

  @JsonProperty("limit")
  private Integer limit = null;

  @JsonProperty("entries")
  private CloudSet entries = null;

  public CloudSetPages offset(Integer offset) {
    this.offset = offset;
    return this;
  }

   /**
   * Offset of the first entry.
   * @return offset
  **/
  @ApiModelProperty(value = "Offset of the first entry.")


  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public CloudSetPages limit(Integer limit) {
    this.limit = limit;
    return this;
  }

   /**
   * Quantity of entries to show.
   * @return limit
  **/
  @ApiModelProperty(value = "Quantity of entries to show.")


  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public CloudSetPages entries(CloudSet entries) {
    this.entries = entries;
    return this;
  }

   /**
   * Get entries
   * @return entries
  **/
  @ApiModelProperty(value = "")

  @Valid

  public CloudSet getEntries() {
    return entries;
  }

  public void setEntries(CloudSet entries) {
    this.entries = entries;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CloudSetPages cloudSetPages = (CloudSetPages) o;
    return Objects.equals(this.offset, cloudSetPages.offset) &&
        Objects.equals(this.limit, cloudSetPages.limit) &&
        Objects.equals(this.entries, cloudSetPages.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offset, limit, entries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CloudSetPages {\n");
    
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    entries: ").append(toIndentedString(entries)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

