from flask import Flask, request
from utils import parse_url_arguments
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
        return get_lunar_phase(args["date"])
    except Exception as ex:
        return (str(ex))

@app.route("/events")
def events():
    return get_events()

if __name__ == "__main__":
    app.run()