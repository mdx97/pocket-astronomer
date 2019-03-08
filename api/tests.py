import unittest
from datetime import date as Date
from utils import parse_url_arguments, extract_date_location

class TestUtils(unittest.TestCase):
    def test_parse_url_arguments(self):
        # Test a URL with arguments.
        url = "http://127.0.0.1:5000/sunrise?date=1-20-2012&lat=40.29&lon=94.81"
        args = parse_url_arguments(url)
        self.assertEqual(args["date"], "1-20-2012")
        self.assertEqual(args["lat"], "40.29")
        self.assertEqual(args["lon"], "94.81")

        # Test a URL with no arguments.
        url = "http://127.0.0.1:5000/sunrise"
        args = parse_url_arguments(url)
        self.assertEqual(args, {})

        # Test a string that is not a URL.
        string = "hello world"
        with self.assertRaises(ValueError):
            args = parse_url_arguments(string)

    def test_construct_date_location(self):
        # Test valid arguments dictionary.
        args = {}
        args["date"] = "1-20-2012"
        args["lat"] = "40.29"
        args["lon"] = "94.81"
        date_location = extract_date_location(args)
        self.assertEqual(date_location.date, Date(2012, 1, 20))
        self.assertEqual(date_location.lat, 40.29)
        self.assertEqual(date_location.lon, 94.81)

        # Test empty arguments dictionary.
        empty = {}
        with self.assertRaises(KeyError):
            extract_date_location(empty)