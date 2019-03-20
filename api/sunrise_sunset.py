import requests
import json
from datetime import datetime, timedelta

class SunriseSunsetTimeRequest:
    def __init__(self, transition, arguments):
        self.transition = transition
        self.arguments = arguments
    
    @property
    def location(self):
        if not "lat" in self.arguments or not "lon" in self.arguments:
            return None
        return SunriseSunsetLocation(float(self.arguments["lat"]), float(self.arguments["lon"]))

    def get(self):
        try:
            url = self._build_url()
            response = requests.get(url)
            data = json.loads(response.text)
            time_string = zero_padded_time(data["results"][self.transition])
            time = datetime.strptime(time_string, "%I:%M:%S %p")
            time -= timedelta(hours=self.location.timezone_modifier)
            return time.strftime("%I:%M:%S %p")
        except Exception as ex:
            return str(ex)
    
    def _missing_arguments(self):
        req = ["lat", "lon", "date"]
        res = []
        for arg in req:
            if not arg in self.arguments:
                res.append(arg)
        return res
    
    def _build_url(self):
        missing = self._missing_arguments()
        if len(missing) > 0:
            raise Exception("Error: missing arguments {} in SunriseSunsetRequest.".format(missing))
        return "https://api.sunrise-sunset.org/json?lat={}&lng={}&date={}".format(self.location.latitude, self.location.longitude, self.arguments["date"])

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