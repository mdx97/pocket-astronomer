from datetime import date as Date
import requests
from bs4 import BeautifulSoup
from constants import MONTH_MAP
from urllib.parse import urlparse
import psycopg2
import sys

if len(sys.argv) < 2:
    print("Missing command line arguments! Format python3 scrape_eclipses.py <Postgres Connection URL>")
    exit(1)
    
url = urlparse(sys.argv[1])
dbname = url.path[1:]
db = psycopg2.connect(dbname=dbname, user=url.username, password=url.password, host=url.hostname, port=url.port)
cursor = db.cursor()

response = requests.get("https://www.timeanddate.com/eclipse/list.html")
soup = BeautifulSoup(response.text, features="lxml")
elements = soup.find_all("a", { "class": "ec-link" })

for el in elements:
    # Extract date from element.
    date_string = el["href"].split("/")[3]
    date_split = date_string.split("-")
    year = int(date_split[0])
    month = MONTH_MAP[date_split[1]]
    day = int(date_split[2])
    date = Date(year, month, day)
    
    # Determine if this element represents a solar eclipse, lunar eclipse, and transits.
    type_el = el.find("span", { "class": "ec-type" })
    t = type_el.text

    # Write to db.
    command = "INSERT INTO events (date, classification) VALUES (%s, %s)"
    cursor.execute(command, (date, t))

db.commit()
cursor.close()
db.close()