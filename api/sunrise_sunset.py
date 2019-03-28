import requests
import json
from datetime import datetime, timedelta
from utils import date_to_string

class SunriseSunsetLocation:
    def __init__(self, latitude, longitude):
        self.latitude = latitude
        self.longitude = longitude
    
    @property
    def timezone_modifier(self):
        return -self.longitude // 15

def zero_padded_time(time_string):
    if len(time_string) == 10:
        return "0" + time_string
    return time_string

def get_sun_transition_time(transition, date, latitude, longitude):
    location = SunriseSunsetLocation(latitude, longitude)
    url = "https://api.sunrise-sunset.org/json?lat={}&lng={}&date={}".format(latitude, longitude, date_to_string(date))
    res = requests.get(url)
    data = json.loads(res.text)
    if "results" not in data:
        raise Exception("Error: no results found!")
    time_string = zero_padded_time(data["results"][transition])
    time = datetime.strptime(time_string, "%I:%M:%S %p")
    time -= timedelta(hours=location.timezone_modifier)
    return time.strftime("%I:%M:%S %p")