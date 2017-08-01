#FROM registry.access.redhat.com/rhscl/python-27-rhel7
FROM docker.io/centos/python-27-centos7:latest

LABEL Name="elastoplast-base (docker.io/centos/python-27-centos7:latest)" \
    Vendor="com.garethahealy" \
    Version="latest" \
    License="Apache License, Version 2.0"

USER root

# Install python dev tools
RUN yum install -y gcc kernel-devel make python-devel python-setuptools

# Create user
RUN groupadd -r elastalert -g 1000 && \
    useradd -u 1000 -r -g elastalert -m -d /deployments -s /sbin/nologin -c "elastalert user" elastalert

# Upgrade pip
RUN cd /deployments \
    && easy_install -U pip \
    && easy_install -U setuptools \
    && pip install --user --upgrade pip

# Clone elastalert from source
RUN cd /deployments && \
    git clone https://github.com/Yelp/elastalert.git --depth 1 --branch v0.0.81 --single-branch && \
    cd elastalert && \
    mkdir rules

RUN chown -R elastalert:elastalert /deployments \
    && chown -R elastalert:elastalert /opt/app-root/src/

# switch to elastalert
USER 1000

# Install
RUN cd /deployments/elastalert \
    && pip install --user -r requirements.txt \
    && python setup.py install --user
