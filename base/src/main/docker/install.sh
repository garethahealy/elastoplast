#!/usr/bin/env bash

###
# #%L
# GarethHealy :: Elastoplast :: Base
# %%
# Copyright (C) 2013 - 2016 Gareth Healy
# %%
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# 
#      http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# #L%
###

# If we dont install the below, the elastalert install fails complaining about missing files :/
easy_install -U setuptools \
    && easy_install -U pip \
    && pip install --upgrade croniter \
    && pip install --upgrade PyStaticConfiguration \
    && pip install --upgrade elasticsearch \
    && pip install --upgrade argparse \
    && pip install --upgrade tlslite \
    && pip install --upgrade oauthlib

cd /deployments/elastalert \
    && python setup.py install \
    && pip install -r requirements.txt
