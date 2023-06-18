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



class FindMatchingFoodAction(Action):
    def name(self) -> Text:
        return "action_find_matching_food"

    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        
        df = pd.read_excel('food_table.xls')

        food_word = tracker.latest_message['text']

        def find_matching_food(food_word):
            best_match = None
            best_ratio = 0

            for food_name in df['name']:
                ratio = fuzz.ratio(food_word, food_name)

                if ratio > best_ratio:
                    best_ratio = ratio
                    best_match = food_name

            return best_match

        # Find the matching food name
        matched_food = find_matching_food(food_word)

        if matched_food is not None:
            food_info = df[df['name'] == matched_food]

            food_name = food_info['name'].values[0]
            calories = food_info['calories'].values[0]
            protein = food_info['protein'].values[0]
            fat = food_info['fat'].values[0]
            size = food_info['size'].values[0]
            type_size = food_info['type_size'].values[0]

            dispatcher.utter_message("Name: " + str(food_name))
            dispatcher.utter_message("Calories: " + str(calories))
            dispatcher.utter_message("Protein: " + str(protein))
            dispatcher.utter_message("Fat: " + str(fat))
            dispatcher.utter_message("Size: " + str(size))
            dispatcher.utter_message("Type/Size: " + str(type_size))
        else:
            dispatcher.utter_message(text="No matching food found.")

        return []
