FROM garethahealy/elastoplast-base:latest

COPY configuration/config.yaml /deployments/elastalert/config.yaml
COPY configuration/rule1.yaml /deployments/elastalert/rules/rule1.yaml
COPY configuration/run.sh /deployments/elastalert/run.sh

USER root
RUN chmod +x /deployments/elastalert/run.sh && \
    chown -R elastalert:elastalert /deployments

# elastalert
USER 1000
ENTRYPOINT ["/deployments/elastalert/run.sh"]
