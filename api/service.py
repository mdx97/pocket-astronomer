from flask import Flask, request
from utils import parse_url_arguments, parse_date
from sunrise_sunset import get_sun_transition_time
from lunar_phase import get_lunar_phase
from event import get_events
from datetime import datetime
import os

app = Flask(__name__)

@app.route("/sunrise")
def sunrise():
    return transition_route("sunrise")

@app.route("/sunset")
def sunset():
    return transition_route("sunset")

def transition_route(transition):
    try:
        args = parse_url_arguments(request.url)
        date = parse_date(args["date"])
        latitude = float(args["lat"])
        longitude = float(args["lon"])
        return get_sun_transition_time(transition, date, latitude, longitude)
    except Exception as ex:
        return str(ex)

@app.route("/lunar_phase")
def lunar_phase():
    try:
        args = parse_url_arguments(request.url)
        date = parse_date(args["date"])
        return get_lunar_phase(date)
    except Exception as ex:
        return str(ex)

@app.route("/events")
def events():
    try:
        args = parse_url_arguments(request.url)
        start_date = parse_date(args["start_date"])
        end_date = parse_date(args["end_date"])
        classification = args["classification"]
        return get_events(start_date, end_date, classification)
    except Exception as ex:
        return str(ex)

if __name__ == "__main__":
    port = os.environ['PORT'] if 'PORT' in os.environ else 5000
    app.run(port=port)