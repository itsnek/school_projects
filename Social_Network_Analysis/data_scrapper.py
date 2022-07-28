from typing import Type
import requests 
from bs4 import BeautifulSoup

URL = "https://flixpatrol.com/demographics/"
page = requests.get(URL)

soup = BeautifulSoup(page.content, "html.parser")

table = soup.find("table",class_="card-table")
soup.fin
table_content = table.find_all("tr",class_="table-group")

for node in table_content:
    Names   = node.find("div",class_="group-hover:underline")
    Type    = node.find("div",class_="flex flex-wrap text-sm leading-6 text-gray-500").find_next_child()
    Countries   = node.find("div",class_="group-hover:underline")
    Premieres   = node.find("title",class_="Premiere")
    Categories   = node.find("div",class_="group-hover:underline")
    Producer   = node.find("div",class_="mx-1 text-gray-600 select-none").find_next_sibling("span")
    Views   = node.find("div",class_="group-hover:underline")

