/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 11/20/20 11:13 AM
 *
 */

package com.base.mvvm;

/**
 * AppConfig
 */
public class AppConfig {

  public static final String API_AUTH_HEADER_KEY_NAME = "Authorization";
  public static final String API_KEY = "Put your api key here";
  public static final String FB_FIELDS = "id, name, first_name, last_name, middle_name, name_format, picture, short_name, email, gender, birthday ,friends, hometown, likes, link, location, photos, posts, videos";
  public static final String[] FB_PERMISSION = new String[]{
      "email",
      "user_birthday",
      "user_events",
      "user_friends",
      "user_gender",
      "user_hometown",
      "user_likes",
      "user_link",
      "user_location",
      "user_photos",
      "user_posts",
      "user_videos"
  };

  public static final int TIME_REMAIN_EVENT = 5;


}
