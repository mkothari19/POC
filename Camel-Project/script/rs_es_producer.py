import requests 
import base64
import json
import re

from datetime import datetime
from requests.adapters import HTTPAdapter
from requests.packages.urllib3.util.retry import Retry

apiclient = requests.Session()
retry = Retry(total=3,read=3,connect=3,backoff_factor=3,status_forcelist=(500,502,504))
adapter = HTTPAdapter(max_retries=retry)
apiclient.mount('http://',adapter)
apiclient.mount('https://',adapter)

json_dir = "/home/ppenetrante/scripts/"
with open(json_dir + "bdu_operational_metadata.json", 'r+') as f:
    payload = json.load(f)

idx_alias="bdu_metadata_"
date_tag = datetime.utcnow().strftime("%m_%Y")
idx_alias += date_tag

apiclient.auth = ('ppenetrante','23!J[xhXZ')
try:
	resp=apiclient.post('https://uscdc01tlmap004.globalservices.bcdtravel.local:9200/bdu_metadata',verify=False,json=payload)
except Exception as x:
	print('Error : ', x.__class__.__name__)
finally:
	print 'done'
