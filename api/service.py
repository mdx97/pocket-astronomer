from flask import Flask, request
from utils import parse_url_arguments, parse_date
from sunrise_sunset import SunriseSunsetTimeRequest
from lunar_phase import get_lunar_phase
from event import get_events
from datetime import datetime

app = Flask(__name__)

@app.route("/sunrise")
def sunrise():
    args = parse_url_arguments(request.url)
    return SunriseSunsetTimeRequest("sunrise", args).get()

@app.route("/sunset")
def sunset():
    args = parse_url_arguments(request.url)
    return SunriseSunsetTimeRequest("sunset", args).get()

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
    app.run()