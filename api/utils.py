from urllib import parse

def parse_url_arguments(url):
    parse_result = parse.urlparse(url)
    if not parse_result.scheme:
        raise ValueError("Error: invalid URL.")
    return dict(parse.parse_qsl(parse_result.query))
