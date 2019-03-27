import json
from database import db
import re

STAGES = [
    "New Moon",
    "Waxing Crescent",
    "First Quarter",
    "Waxing Gibbous",
    "Full Moon",
    "Waning Gibbous",
    "Last Quarter",
    "Waning Crescent"
]

def get_lunar_phase(date_string):
    if not re.match("[0-9]{4}-[0|1][1-9]-[0-3][1-9]", date_string):
        return "Error: invalid date format."
    query = "SELECT * FROM lunar_phases WHERE date=%s"
    cursor = db.cursor()
    cursor.execute(query, [date_string])
    result = cursor.fetchone()
    if not result:
        return "Error: no results found!"
    cursor.close()
    return json.dumps({"date": date_string, "phase": STAGES[result[1]]})