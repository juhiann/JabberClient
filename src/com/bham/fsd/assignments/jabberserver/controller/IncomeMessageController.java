package com.bham.fsd.assignments.jabberserver.controller;

import com.bham.fsd.assignments.jabberserver.JabberMessage;

public class IncomeMessageController
{
    /**
     * All possible income valid messages in a String Array
     */
    private static final String[] INCOME_MESSAGES =
    {
        "signin",
        "register",
        "signout",
        "timeline"
    };

    /**
     * Is the message from the server valid or not?
     * @param jMsg
     */
    public static void isValidMessage(JabberMessage jMsg)
    {
        for (String msg : INCOME_MESSAGES)
        {
            if(jMsg.getMessage().equals(msg))
            {

            }
        }
    }

}
