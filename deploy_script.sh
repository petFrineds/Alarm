git pull
docker build -t alarm-backend .
docker tag alarm-backend:latest 811288377093.dkr.ecr.$AWS_REGION.amazonaws.com/alarm-backend:latest
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin 811288377093.dkr.ecr.us-west-2.amazonaws.com/
docker push 811288377093.dkr.ecr.us-west-2.amazonaws.com/alarm-backend:latest
kubectl delete -f manifests/alarm-deployment.yaml
kubectl apply -f manifests/alarm-deployment.yaml
kubectl get pod
kubectl apply -f manifests/alarm-service.yaml
kubectl get service