from rest_framework.response import Response
from rest_framework.decorators import api_view
from base.models import Species
from .serializers import SpeciesSerializer

@api_view(["GET"])
def getData(request):
    species = Species.objects.all()
    serializer = SpeciesSerializer(species, many=True)
    return Response(serializer.data) 

@api_view(['POST'])
def addData(request):
    serializer = SpeciesSerializer(data=request.data)
    if serializer.is_valid():
        serializer.save()

    return Response(serializer.data)