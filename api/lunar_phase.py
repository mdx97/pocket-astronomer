import json
from database import db
from utils import parse_date, date_to_string

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

class LunarPhase:
    def __init__(self, date, stage):
        self.date = date
        self.stage = stage
    
    def serialize(self):
        return {"date": date_to_string(self.date), "phase": STAGES[self.stage]}

def get_lunar_phase(date_string):
    date = parse_date(date_string)
    query = "SELECT * FROM lunar_phases WHERE date=%s"
    cursor = db.cursor()
    cursor.execute(query, [date_string])
    result = cursor.fetchone()
    if not result:
        raise Exception("Error: no results found!")
    cursor.close()
    phase = LunarPhase(date, result[1])
    return json.dumps(phase.serialize())