package io.jabberapp.client.controller;

public class MessageValidator
{
    private static final String invalidMessageContentResponse = "invalidMessage";

    public static boolean isValidMessage(String prefix, String[] protocol)
    {
        for(String s : protocol)
        {
            if(prefix.equals(s))
            {
                return true;
            }
        }
        return false;
    }

    public static String combinePrefixAndSuffix(String prefix, String data, String[] protocol)
    {
        if (isValidMessage(prefix, protocol))
        {
            return prefix + " " + data;
        }
        else
            return invalidMessageContentResponse;
    }
}
