# This files contains your custom actions which can be used to run
# custom Python code.
#
# See this guide on how to implement these action:
# https://rasa.com/docs/rasa/custom-actions
from typing import Any, Text, Dict, List
from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher
import pandas as pd
import numpy as np
from data_connectivity import *
import math

class ActionAskForBreakfast(Action):
    def name(self) -> Text:
        return "action_ask_for_breakfast"

    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:

        id = tracker.sender_id
        calories = get_calories(id)
        
        breakfast_calory = (25.0*calories)/100.0
        meal = get_breakfast_meal()
        dispatcher.utter_message(text=meal[0][1])
        if meal[0][2] != 'nan':
            dispatcher.utter_message(text="ممكن تبص على طريقة الطبخ من خلال اللينك دا "+meal[0][2])
        dispatcher.utter_message(text="المكونات كالتالي")
        food_ids = get_food_breakast_id(meal[0][0])
        m = len(food_ids)
        for i in range (m):
            food_item = get_food(food_ids[i][0])

            food_calory = (food_ids[i][1]*breakfast_calory)/100.0
            
            grams = (food_calory*100) / food_item[0][2]
            grams = math.floor(math)
            dispatcher.utter_message(text=food_item[0][1]+" بمقدار " + str(grams) +" جرام ")

        return []
    
class ActionAskForSnack(Action):
    def name(self) -> Text:
        return "action_ask_for_snack"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        
        id = tracker.sender_id
        calories = get_calories(id)
    
        return []

class ActionAskForLunch(Action):
    def name(self) -> Text:
        return "action_ask_for_lunch"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        id = tracker.sender_id
        calories = get_calories(id)
        lunch_calory = (35.0*calories)/100.0

        meal = get_lunch_meal()

        dispatcher.utter_message(text=meal[0][1])
        if meal[0][2] != 'nan':
            dispatcher.utter_message(text="ممكن تبص على طريقة الطبخ من خلال اللينك دا "+meal[0][2])
        dispatcher.utter_message(text="المكونات كالتالي")

        food_ids = get_food_lunch_id(meal[0][0])

        m = len(food_ids)
        for i in range (m):
            food_item = get_food(food_ids[i][0])

            food_calory = (food_ids[i][1]*lunch_calory)/100.0
            
            grams = (food_calory*100) / food_item[0][2]
            grams = math.floor(math)
            dispatcher.utter_message(text=food_item[0][1]+" بمقدار " + str(grams) +" جرام ")

        return []
    
### get dinner meal #######    
class ActionAskForDinner(Action):
    def name(self) -> Text:
        return "action_ask_for_dinner"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        id = tracker.sender_id
        calories = get_calories(id)
        dinner_calory = (20.0*calories)/100.0
        meal = get_dinner_meal()
        dispatcher.utter_message(text=meal[0][1])
        if meal[0][2] != 'nan':
            dispatcher.utter_message(text="ممكن تبص على طريقة الطبخ من خلال اللينك دا "+meal[0][2])
        dispatcher.utter_message(text="المكونات كالتالي")
        food_ids = get_food_dinner_id(meal[0][0])
        m = len(food_ids)
        for i in range (m):
            food_item = get_food(food_ids[i][0])

            food_calory = (food_ids[i][1]*dinner_calory)/100.0
            
            grams = (food_calory*100) / food_item[0][2]
            grams = math.floor(math)
            dispatcher.utter_message(text=food_item[0][1]+" بمقدار " + str(grams) +" جرام ")

        return []
