from urllib import parse
from datetime import datetime

def parse_url_arguments(url):
    parse_result = parse.urlparse(url)
    if not parse_result.scheme:
        raise ValueError("Error: invalid URL.")
    return dict(parse.parse_qsl(parse_result.query))

def parse_date(date_string):
    return datetime.strptime(date_string, "%Y-%m-%d")

def date_to_string(date):
    return date.strftime("%Y-%m-%d")