from date_location import DateLocation
from flask import request
from urllib import parse
from datetime import datetime, date as Date
import requests
import json

def parse_url_arguments(url):
    parse_result = parse.urlparse(url)
    if not parse_result.scheme:
        raise ValueError("Error: invalid URL.")
    return dict(parse.parse_qsl(parse_result.query))

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


def build_sunrise_sunset_url(date_location):
    return "https://api.sunrise-sunset.org/json?lat={}&lng={}&date={}".format(date_location.lat, date_location.lon, date_location.date)
    
def extract_date_location(arguments):
    try:
        date = datetime.strptime(arguments["date"], "%m-%d-%Y").date()
        lat = float(arguments["lat"])
        lon = float(arguments["lon"])
        return DateLocation(date, lat, lon)
    except KeyError as ex:
        raise KeyError("Error: no argument found for {}".format(str(ex)))
