from date_location import DateLocation
from urllib import parse
from datetime import datetime

def parse_url_arguments(url):
    parse_result = parse.urlparse(url)
    if not parse_result.scheme:
        raise ValueError("Error: invalid URL.")
    return dict(parse.parse_qsl(parse_result.query))

def build_sunrise_sunset_url(date_location):
    return "https://api.sunrise-sunset.org/json?lat={}&lng={}&date={}".format(date_location.lat, date_location.lon, date_location.date)
    
def extract_date_location(arguments):
    try:
        date = parse_date(arguments["date"])
        lat = float(arguments["lat"])
        lon = float(arguments["lon"])
        return DateLocation(date, lat, lon)
    except KeyError as ex:
        raise KeyError("Error: no argument found for {}".format(str(ex)))

def parse_date(date_string):
    return datetime.strptime(date_string, "%m-%d-%Y").date()