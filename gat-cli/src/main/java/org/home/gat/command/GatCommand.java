package org.home.gat.command;

import io.airlift.airline.Arguments;
import io.airlift.airline.model.GlobalMetadata;
import org.springframework.context.ApplicationContext;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public abstract class GatCommand {

    public abstract void run(ApplicationContext applicationContext);

}
