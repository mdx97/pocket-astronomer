import json
from database import db

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
    query = "SELECT * FROM lunar_phases WHERE date=%s"
    cursor = db.cursor()
    cursor.execute(query, [date_string])
    result = cursor.fetchone()
    print(result)
    if not result:
        return "Error: no results found!"
    cursor.close()
    return json.dumps({"date": date_string, "phase": STAGES[result[1]]})