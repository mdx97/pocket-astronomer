from flask import Flask, request
from utils import parse_url_arguments
from sunrise_sunset import SunriseSunsetRequest
from lunar_phase import get_lunar_phase
from event import get_events

app = Flask(__name__)

@app.route("/sunrise")
def sunrise():
    args = parse_url_arguments(request.url)
    req = SunriseSunsetRequest("sunrise", args)
    return req.get()

@app.route("/sunset")
def sunset():
    args = parse_url_arguments(request.url)
    req = SunriseSunsetRequest("sunset", args)
    return req.get()

@app.route("/lunar_phase")
def lunar_phase():
    return get_lunar_phase()

@app.route("/events")
def events():
    return get_events()

if __name__ == "__main__":
    app.run()