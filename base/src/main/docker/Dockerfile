FROM registry.access.redhat.com/rhscl/python-27-rhel7

USER root

RUN yum install -y gcc kernel-devel make python-devel python-setuptools

RUN groupadd -r elastalert -g 1000 && \
    useradd -u 1000 -r -g elastalert -m -d /deployments -s /sbin/nologin -c "elastalert user" elastalert

RUN cd /deployments && \
    git clone https://github.com/Yelp/elastalert.git --depth 1 --branch v0.0.81 --single-branch && \
    cd elastalert && \
    mkdir rules

# Weird issue where the contents of this install script wouldnt work as being docker RUN commands
COPY install.sh /deployments/elastalert/
RUN chmod +x /deployments/elastalert/install.sh && \
    /deployments/elastalert/install.sh && \
    rm -f /deployments/elastalert/install.sh

RUN chown -R elastalert:elastalert /deployments

USER 1000


