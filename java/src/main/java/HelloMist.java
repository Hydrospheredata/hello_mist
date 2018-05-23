import static mist.api.jdsl.Jdsl.*;

import mist.api.Handle;
import mist.api.MistFn;
import mist.api.jdsl.JEncoders;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

public class HelloMist extends MistFn {

  Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public Handle handle() {
    return withArgs(intArg("samples", 10000)).
           withMistExtras().
           onSparkContext((n, extras, sc) -> {

         logger.info("Hello Mist started with samples:" + n);
         List<Integer> l = new ArrayList<>(n);
         for (int i = 0; i < n ; i++) {
             l.add(i);
         }

         long count = sc.parallelize(l).filter(i -> {
             double x = Math.random();
             double y = Math.random();
             return x*x + y*y < 1;
         }).count();

         double pi = (4.0 * count) / n;
         return pi;
    }).toHandle(JEncoders.doubleEncoder());
  }
}
