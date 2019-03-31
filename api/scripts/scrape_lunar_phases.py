from datetime import date, timedelta
from bs4 import BeautifulSoup
from selenium import webdriver
import os
import psycopg2
from config import POSTGRES_CONN_STRING

START_DATE = date.today()
END_DATE = START_DATE + timedelta(days=30)
browser = webdriver.Chrome('C:\\ChromeDriver\\chromedriver.exe')
db = psycopg2.connect(POSTGRES_CONN_STRING)

def scrape_lunar_phase(date):
    url = "https://www.moongiant.com/phase/{}/{}/{}".format(date.month, date.day, date.year)
    browser.get(url)
    data = browser.page_source
    soup = BeautifulSoup(data, features="html.parser")
    moon_details = soup.find(id="moonDetails")
    phase = moon_details.find('span')
    phase_name = phase.text
    return phase_name

stage_dict = {
    "New Moon": 0,
    "Waxing Crescent": 1,
    "First Quarter": 2,
    "Waxing Gibbous": 3,
    "Full Moon": 4,
    "Waning Gibbous": 5,
    "Last Quarter": 6,
    "Waning Crescent": 7
}

date_iter = START_DATE
cursor = db.cursor()

while date_iter < END_DATE:
    phase_name = scrape_lunar_phase(date_iter)
    stage = stage_dict[phase_name]
    insert = "INSERT INTO lunar_phases (date, stage) VALUES (%s, %s)"
    cursor.execute(insert, (date_iter, stage))
    date_iter += timedelta(days=1)

db.commit()
cursor.close()
db.close()