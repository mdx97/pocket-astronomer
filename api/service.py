from flask import Flask, request
from api import get_sun_transition_time, get_lunar_phase, get_events

app = Flask(__name__)

@app.route("/")
def index():
    return "Hello, Flask!"

@app.route("/sunrise")
def sunrise():
    return get_sun_transition_time("sunrise")

@app.route("/sunset")
def sunset():
    return get_sun_transition_time("sunset")

@app.route("/lunar_phase")
def lunar_phase():
    return get_lunar_phase()

@app.route("/events")
def events():
    return get_events()

if __name__ == "__main__":
    app.run()