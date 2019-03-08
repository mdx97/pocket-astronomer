from flask import Flask, request
from utils import get_sun_transition_time

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

if __name__ == "__main__":
    app.run()