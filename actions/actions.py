# This files contains your custom actions which can be used to run
# custom Python code.
#
# See this guide on how to implement these action:
# https://rasa.com/docs/rasa/custom-actions
from typing import Any, Text, Dict, List
from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher
from datetime import date
import calendar
import pandas as pd
import numpy as np
from data_connectivity import *
current_date = date.today()
day = calendar.day_name[current_date.weekday()]
dataframe1 = pd.read_excel(day+'.xlsx')
data = np.array(dataframe1)
m = len(data)
class ActionAskForBreakfast(Action):
    def name(self) -> Text:
        return "action_ask_for_breakfast"

    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:

        id = tracker.sender_id
        calories = get_calories(id)
        var = ""
        for i in range (m):
             if data[i][0] != -1.0 :
                var=data[i][0]+" بمقدار "+ str((calories/1000.0)*data[i][1]) + " " + str(data[i][2])+"\n"
                dispatcher.utter_message(text=var)

        return []
class ActionAskForSnack1(Action):

    def name(self) -> Text:
        return "action_ask_for_snack1"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        id = tracker.sender_id
        calories = get_calories(id)
        var = ""
        for i in range (m):
             if data[i][5] != -1.0 :
                var=data[i][5]+" بمقدار "+ str((calories/1000.0)*data[i][6]) + " " + str(data[i][7])+"\n"
                dispatcher.utter_message(text=var)
        
        return []
class ActionAskForLunch(Action):
    def name(self) -> Text:
        return "action_ask_for_lunch"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        id = tracker.sender_id
        calories = get_calories(id)
        var = ""
        for i in range (m):
             if data[i][10] != -1.0 :
                 var=data[i][10]+" بمقدار "+ str((calories/1000.0)*data[i][11]) + " " + str(data[i][12])+"\n"
                 dispatcher.utter_message(text=var)
        
        return []
class ActionAskForSnack2(Action):

    def name(self) -> Text:
        return "action_ask_for_snack2"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        id = tracker.sender_id
        calories = get_calories(id)
        var = ""
        for i in range (m):
             if data[i][15] != -1.0 :
                var=data[i][15]+" بمقدار "+ str((calories/1000.0)*data[i][16]) + " " + str(data[i][17])+"\n"
                dispatcher.utter_message(text=var)
        
        return []
class ActionAskForDinner(Action):
    def name(self) -> Text:
        return "action_ask_for_dinner"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        id = tracker.sender_id
        calories = get_calories(id)
        var = ""
        for i in range (m):
            if data[i][20] != -1.0 :
                 var=data[i][20]+" بمقدار "+ str((calories/1000.0)*data[i][21]) + " " + str(data[i][22])+"\n"
                 dispatcher.utter_message(text=var)
        
        return []
