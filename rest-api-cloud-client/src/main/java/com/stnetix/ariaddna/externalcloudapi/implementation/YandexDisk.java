package com.stnetix.ariaddna.externalcloudapi.implementation;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stnetix.ariaddna.externalcloudapi.AccessToken;
import com.stnetix.ariaddna.externalcloudapi.cloudinterface.iAbstractCloud;
import okhttp3.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

//TODO Выделить в отдельный модуль все хосты и огрызки URL'ов
//TODO Отправлять в БД настройки облака
//TODO Продумать способ хранения множества аккаунтов

public class YandexDisk implements iAbstractCloud {

    private AccessToken accessToken;

    private static final String HOST_URL = "cloud-api.yandex.net";

    private static final String OAUTH_HOST = "oauth.yandex.ru";

    private static final String CLIENT_ID = "141588389ce24e4e80e6ccd5db81c7a6";

    private static final String CLIENT_SECRET = "7fbc2f7214b64d53809249b5534291d2";

    private String verificationCode = "4306705";

    private String tempAccessToken = "AQAAAAAeFNwNAARF-Tixdh81n007h52kXupp1qg";


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client;

    public YandexDisk() {
        client = new OkHttpClient();
    }

    private HttpUrl constructOAuthUrl(){
        return new HttpUrl.Builder()
                .scheme("https")
                .host(OAUTH_HOST)
                .addPathSegment("authorize")
                .addQueryParameter("response_type", "code")
                .addQueryParameter("client_id", CLIENT_ID)
                .build();
    }

    private void openOAuthPage(){
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(constructOAuthUrl().uri());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVerificationCode(String value){
        this.verificationCode = value;
    }

    public String getVerificationCode(){
        return this.verificationCode;
    }

    @Override
    public JsonObject uploadFile(File path) {
        return null;
    }

    @Override
    public JsonObject uploadExternalFile(File path, URL url) {
        return null;
    }

    @Override
    public JsonObject downloadFile(File path) {
        return null;
    }

    @Override
    public JsonObject copyFile(File from, File to) {
        return null;
    }

    @Override
    public JsonObject moveFile(File from, File to) {
        return null;
    }

    @Override
    public JsonObject createDirectory(File path) {
        JsonParser parser = new JsonParser();
        JsonObject result = new JsonObject();

        String folderName = "path: TEST";
        Request request = new Request.Builder()
                .url("https://" + HOST_URL + "/v1/disk/resources?path=TEST")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "OAuth " + tempAccessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if(response.code() == 200) {

                result = parser.parse(response.body().string()).getAsJsonObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public JsonObject deleteResource(File path) {
        return null;
    }

    @Override
    public JsonObject getResourceMetadata(File path) {
        return null;
    }

    @Override
    public JsonObject getCloudStorageMetadata() {
        JsonParser parser = new JsonParser();
        JsonObject result = new JsonObject();
        Request request = new Request.Builder()
                .url("https://" + HOST_URL + "/v1/disk")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "OAuth " + tempAccessToken)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            if(response.code() == 200) {

                result = parser.parse(response.body().string()).getAsJsonObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public JsonObject getCloudStorageAuthToken() {
        if(verificationCode == null) {
            //FIXME Somehow we need to set the verification code value that a user will see at a page
            //How to call setVerificationCode(..) _conveniently_? Maybe popup-window?
            openOAuthPage();
        } else {
            String authCred = Credentials.basic(CLIENT_ID, CLIENT_SECRET);
            String bodyParam = "grant_type=authorization_code&code=" + verificationCode;

            Request request = new Request.Builder()
                    .url("https://" + OAUTH_HOST + "/token")
                    .header("Authorization", authCred)
                    .post(RequestBody.create(JSON, bodyParam ))
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if(response.code() == 200) {
                    JsonParser parser = new JsonParser();
                    JsonObject o = parser.parse(response.body().string()).getAsJsonObject();
                    accessToken = AccessToken.buildAccessToken(o);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    String madeJson() {
        return "grant_type=authorization_code"
                + "&code=" + verificationCode;
    }

    @Override
    public JsonObject revokeCloudStorageAuthToken() {
        return null;
    }

    public static void main(String[] args) {
        YandexDisk yandexDisk = new YandexDisk();
    //    yandexDisk.getCloudStorageAuthToken();
     //yandexDisk.getCloudStorageAuthToken();
       yandexDisk.createDirectory(null);
    }
}
