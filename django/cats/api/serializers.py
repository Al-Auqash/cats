from rest_framework import serializers
from base.models import Species

class SpeciesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Species
        field = "__all__"
