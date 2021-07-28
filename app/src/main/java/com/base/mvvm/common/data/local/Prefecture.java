/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/26/19 9:25 AM
 *
 */

package com.base.mvvm.common.data.local;

import com.google.gson.annotations.SerializedName;

/**
 * model Prefecture
 */
public class Prefecture {

  private long id;

  private String name;

  @SerializedName("short")
  private String shortName;

  @SerializedName("kana")
  private String kanaName;

  @SerializedName("en")
  private String enName;

  private long[] neighbor;


  public Prefecture(long id, String name, String shortName, String kanaName, String enName, long[] neighbor) {
    this.id = id;
    this.name = name;
    this.shortName = shortName;
    this.kanaName = kanaName;
    this.enName = enName;
    this.neighbor = neighbor;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getKanaName() {
    return kanaName;
  }

  public void setKanaName(String kanaName) {
    this.kanaName = kanaName;
  }

  public String getEnName() {
    return enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  public long[] getNeighbor() {
    return neighbor;
  }

  public void setNeighbor(long[] neighbor) {
    this.neighbor = neighbor;
  }
}

