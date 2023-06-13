from django.contrib import admin
from .models import Species

# Register your models here.
@admin.register(Species)
class SpeciesAdmin(admin.ModelAdmin):
    pass