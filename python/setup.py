import os
from setuptools import setup, find_packages

setup(
    name='hello-mist',
    version = '0.0.2',
    packages=find_packages(exclude=['tests']),
    install_requires=["pyspark==2.3.0", "mistpy"]
)
