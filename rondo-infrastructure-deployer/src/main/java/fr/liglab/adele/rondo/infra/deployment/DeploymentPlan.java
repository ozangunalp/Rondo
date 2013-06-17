package fr.liglab.adele.rondo.infra.deployment;

import fr.liglab.adele.rondo.infra.model.ResourceReference;

import java.util.LinkedList;

/**
 * The Deployment Plan consists of a list of resource states that the deployment should pass in order to succeed.
 * It describes the process of a deployment.
 * This implementation simply extends a {@code LinkedList} of {@code ResourceReference}s.
 */
public class DeploymentPlan extends LinkedList<ResourceReference> {
}
