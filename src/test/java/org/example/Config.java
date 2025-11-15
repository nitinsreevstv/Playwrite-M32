package org.example;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.load();

    public static final String URL = dotenv.get("URL");
    public static final String LoginEmail = dotenv.get("LoginEmail");
    public static final String LoginPassword = dotenv.get("LoginPassword");
    public static final String BaseEmail = dotenv.get("BaseEmail");
    public static final String DefaultPassword = dotenv.get("DefaultPassword");

}
