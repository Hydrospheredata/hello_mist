from mistpy.decorators import *
import random

@with_args(
    arg("samples", type_hint=int)
)
@on_spark_context
def hello_mist(sc, samples):
    def inside(p):
        x, y = random.random(), random.random()
        return x * x + y * y < 1

    count = sc.parallelize(xrange(0, samples)) \
        .filter(inside).count()

    pi = 4.0 * count / samples
    return {'result': pi}
