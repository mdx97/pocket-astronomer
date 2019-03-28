from urllib import parse
from datetime import datetime
from args import Args

def parse_url_arguments(url):
    parse_result = parse.urlparse(url)
    if not parse_result.scheme:
        raise ValueError("Error: invalid URL.")
    d = dict(parse.parse_qsl(parse_result.query))
    args = Args()
    for key, val in d.items():
        args[key] = val
    return args
    
def parse_date(date_string):
    return datetime.strptime(date_string, "%Y-%m-%d")

def date_to_string(date):
    return date.strftime("%Y-%m-%d")

def datetime_to_string(dt):
    return dt.strftime("%Y-%m-%d %H:%M:%S")