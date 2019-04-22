import requests
from bs4 import BeautifulSoup
from constants import MONTH_MAP
import sys
import psycopg2
from urllib.parse import urlparse
from datetime import date as Date

if len(sys.argv) < 2:
    print("Missing command line arguments! Format python3 scrape_eclipses.py <Postgres Connection URL>")
    exit(1)

url = urlparse(sys.argv[1])
dbname = url.path[1:]
db = psycopg2.connect(dbname=dbname, user=url.username, password=url.password, host=url.hostname, port=url.port)
cursor = db.cursor()

response = requests.get("http://www.earthriseinstitute.org/inboundcoms.html")
soup = BeautifulSoup(response.text, features="lxml")
elements = soup.find_all("b")[2:]

for el in elements:
    tokens = (el.text).split('(')
    if len(tokens) != 2: 
        continue
    name = (tokens[0].split('\xa0'))[0]
    datestr = ' '.join((tokens[1]).split(' ')[1:])[:-1]
    datesplit = datestr.split()
    if len(datesplit) != 3: 
        continue
    year = int(datesplit[0])
    month = MONTH_MAP[datesplit[1].lower()]
    day = int(datesplit[2])
    date = Date(year, month, day)
    command = "INSERT INTO events (date, classification, name) VALUES (%s, 'Comet', %s)"
    cursor.execute(command, (date, name))

db.commit()
cursor.close()
db.close()