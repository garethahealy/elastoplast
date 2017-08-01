FROM garethahealy/elastoplast-base:latest

LABEL Name="elastoplast-rule1 (garethahealy/elastoplast-base:latest)" \
    Vendor="com.garethahealy" \
    Version="latest" \
    License="Apache License, Version 2.0"

COPY configuration/config.yaml /deployments/elastalert/config.yaml
COPY configuration/rule1.yaml /deployments/elastalert/rules/rule1.yaml
COPY configuration/run.sh /deployments/elastalert/run.sh
COPY fix-permissions /fix-permissions

USER root
RUN chmod +x /fix-permissions && \
    /fix-permissions /deployments

ENTRYPOINT ["/deployments/elastalert/run.sh"]
