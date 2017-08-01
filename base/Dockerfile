FROM docker.io/centos/python-27-centos7:latest

LABEL Name="elastoplast-base (docker.io/centos/python-27-centos7:latest)" \
    Vendor="com.garethahealy" \
    Version="latest" \
    License="Apache License, Version 2.0"

USER root

# Install python dev tools
RUN yum install -y gcc kernel-devel make python-devel python-setuptools && \
    yum clean all

# Install python 2.7.13
## NOTE: python included with base image fails for some reason, see error in comments: https://hub.docker.com/r/centos/python-27-centos7
RUN cd /usr/src  && \
    wget https://www.python.org/ftp/python/2.7.13/Python-2.7.13.tgz && \
    tar xzf Python-2.7.13.tgz && \
    cd Python-2.7.13 && \
    ./configure && \
    make altinstall && \
    curl https://bootstrap.pypa.io/get-pip.py | /usr/local/bin/python2.7 -

# Create virutal env for python
RUN /usr/local/bin/pip install virtualenv && \
    virtualenv /deployments/yelp

# Clone elastalert from source
RUN git clone https://github.com/Yelp/elastalert.git --depth 1 --branch v0.1.18 --single-branch /deployments/yelp/elastalert && \
    mkdir /deployments/yelp/elastalert/rules

# Install
RUN source /deployments/yelp/bin/activate && \
    pip install -r /deployments/yelp/elastalert/requirements.txt && \
    python2.7 /deployments/yelp/elastalert/setup.py install
