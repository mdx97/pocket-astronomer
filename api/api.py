from utils import *
from lunar_phase import LunarPhase
from flask import request
import requests
import json

def get_sun_transition_time(transition):
    try:
        args = parse_url_arguments(request.url)
        date_location = extract_date_location(args)
        url = build_sunrise_sunset_url(date_location)
        response = requests.get(url)
        data = json.loads(response.text)
        return data["results"][transition]
    except Exception as ex:
        return str(ex)

def get_lunar_phase():
    # TODO: This is just a placeholder method.
    return json.dumps(LunarPhase().to_dict())

def get_events():
    return json.dumps([])