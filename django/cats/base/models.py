from django.db import models

# Create your models here.
class Species(models.Model):
    name = models.CharField(max_length=200)
    created = models.DateTimeField(auto_now_add=True)
    
    class Meta: 
        verbose_name = "Species"
        verbose_name_plural = "Species"