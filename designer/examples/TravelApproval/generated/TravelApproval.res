% PARAMETERS:

	Protocol:			 TravelApproval
	Problem category:		 if

	Compound types:			 on
	Step compression:		 on
	Intruder Knowledge As Axioms:	 off
	Weak Type-Flaws (iff newgp):	 off

	Technique:			 Graphplan-based Encoding using the EFA schema
	Min Steps:			 0
	Max Steps:			 80
	Delta Steps:			 1
	Level Mutex:			 0
	Solver:				 minisat

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% SATE file generated in 0.06 sec...

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

PHASE: INIT

* SUB-PHASE: Schemes Generation and Translation

  STATISTICS			CLAUSES	RUNTIME(sec)
  Initial Facts:		25	0.0
					------
  Total:				0.0

* SUB-PHASE: Build Graph

  STATISTICS			LAYER	LEVELED OFF	RUNTIME(sec)
							------
  Total:						0.0

* SUB-PHASE: Assert Possible Goals Instances

  STATISTICS				RUNTIME(sec)
  Total:				0.0

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

PHASE: LOOP ITERATION 1

* SUB-PHASE: Search for SAT models..

  Find models procedure skipped.

* SUB-PHASE: Build Graph

  STATISTICS			LAYER	LEVELED OFF	RUNTIME(sec)
				0	no		0.0
							------
  Total:						0.0

* SUB-PHASE: Assert Possible Goals Instances

  STATISTICS				RUNTIME(sec)
  Total:				0.0

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

PHASE: LOOP ITERATION 2

* SUB-PHASE: Search for SAT models..

  Find models procedure skipped.

* SUB-PHASE: Build Graph

  STATISTICS			LAYER	LEVELED OFF	RUNTIME(sec)
				1	no		0.02
							------
  Total:						0.02

* SUB-PHASE: Assert Possible Goals Instances

  STATISTICS				RUNTIME(sec)
  Total:				0.0

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

PHASE: LOOP ITERATION 3

* SUB-PHASE: Search for SAT models..

  Find models procedure skipped.

* SUB-PHASE: Build Graph

  STATISTICS			LAYER	LEVELED OFF	RUNTIME(sec)
				2	no		0.0
							------
  Total:						0.0

* SUB-PHASE: Assert Possible Goals Instances

  STATISTICS				RUNTIME(sec)
  Total:				0.0

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

PHASE: LOOP ITERATION 4

* SUB-PHASE: Search for SAT models..

  Find models procedure skipped.

* SUB-PHASE: Build Graph

  STATISTICS			LAYER	LEVELED OFF	RUNTIME(sec)
				3	no		0.02
							------
  Total:						0.02

* SUB-PHASE: Assert Possible Goals Instances

  STATISTICS				RUNTIME(sec)
  Total:				0.0

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

PHASE: LOOP ITERATION 5

* SUB-PHASE: Search for SAT models..

  Find models procedure skipped.

* SUB-PHASE: Build Graph

  STATISTICS			LAYER	LEVELED OFF	RUNTIME(sec)
				4	no		0.0
							------
  Total:						0.0

* SUB-PHASE: Assert Possible Goals Instances

  STATISTICS				RUNTIME(sec)
  Total:				0.0

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

PHASE: LOOP ITERATION 6

* SUB-PHASE: Search for SAT models..

  Find models procedure skipped.

* SUB-PHASE: Build Graph

  STATISTICS			LAYER	LEVELED OFF	RUNTIME(sec)
				5	no		0.02
							------
  Total:						0.02

* SUB-PHASE: Assert Possible Goals Instances

  STATISTICS				RUNTIME(sec)
  Total:				0.0

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

PHASE: LOOP ITERATION 7

* SUB-PHASE: Search for SAT models..

  Find models procedure skipped.

* SUB-PHASE: Build Graph

  STATISTICS			LAYER	LEVELED OFF	RUNTIME(sec)
				6	no		0.02
							------
  Total:						0.02

* SUB-PHASE: Assert Possible Goals Instances

  STATISTICS				RUNTIME(sec)
  Total:				0.0

* SUB-PHASE: Schemes Generation and Translation

  STATISTICS			CLAUSES	RUNTIME(sec)
  Goals:			7	0.0
  Refinement Schema:		0	0.0
  Horn Clauses Axioms:		252	0.04
  User Axioms:			0	0.0
  Ape Schema:			768	0.06
  Explanatory Frame Schema:	789	0.08
					------
  Total:				0.18

* SUB-PHASE: Solver SAT formula Updated

  STATISTICS
  Depth:	7
  Atoms:	746
  Clauses:	1841

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

PHASE: LOOP ITERATION 8

* SUB-PHASE: Search for SAT models..

  Found 1 models in 0.0 sec.

* SUB-PHASE: Models into Partial Order Plans (POPs)

  STATISTICS		RUNTIME(sec)
  model2pop 1		0.06
			------
  Total:		0.06

* SUB-PHASE: Partial Order Plans (POPs) validations

  STATISTICS		VALID	RUNTIME(sec)
  POP 1:		true	0.0
				------
  Total:			0.0

* SUB-PHASE: Partial Order Plans (POPs) printing

  --------------------------------------------------------------------
  pop 1:

  GOALS: [sod_securitySod1_1(user1_manager,fnat(n2,0,0),fnat(n3,0,0))]

  Step 0:	 [sc_w_usertask1_1(0)]
  Step 1:	 [sc_authorizeTaskExecution_1(user1_clerk,clerk,usertask1,fnat(n0,0,0)),rbac_ac(user1_clerk,clerk,usertask1),rbac_ac(user1_manager,manager,usertask2),rbac_ac(user1_manager,manager,usertask3),rbac_ac(user2_clerk,clerk,usertask1),rbac_ac(user2_manager,manager,usertask2),rbac_ac(user2_manager,manager,usertask3)]
  Step 2:	 [sc_h_taskExecution_1(user1_clerk,clerk,usertask1,fnat(n0,0,0),in_usertask1,out_usertask1),rbac_ac(user1_clerk,clerk,usertask1),rbac_ac(user1_manager,manager,usertask2),rbac_ac(user1_manager,manager,usertask3),rbac_ac(user2_clerk,clerk,usertask1),rbac_ac(user2_manager,manager,usertask2),rbac_ac(user2_manager,manager,usertask3)]
  Step 3:	 [sc_w_parallelgateway1_1(fnat(n0,0,0)),rbac_ac(user1_clerk,clerk,usertask1),rbac_ac(user1_manager,manager,usertask2),rbac_ac(user1_manager,manager,usertask3),rbac_ac(user2_clerk,clerk,usertask1),rbac_ac(user2_manager,manager,usertask2),rbac_ac(user2_manager,manager,usertask3)]
  Step 4:	 [sc_w_usertask2_1(0),sc_w_usertask3_1(0),rbac_ac(user1_clerk,clerk,usertask1),rbac_ac(user1_manager,manager,usertask2),rbac_ac(user1_manager,manager,usertask3),rbac_ac(user2_clerk,clerk,usertask1),rbac_ac(user2_manager,manager,usertask2),rbac_ac(user2_manager,manager,usertask3)]
  Step 5:	 [sc_authorizeTaskExecution_1(user1_manager,manager,usertask2,fnat(n2,0,0)),sc_authorizeTaskExecution_1(user1_manager,manager,usertask3,fnat(n3,0,0)),rbac_ac(user1_clerk,clerk,usertask1),rbac_ac(user1_manager,manager,usertask2),rbac_ac(user1_manager,manager,usertask3),rbac_ac(user2_clerk,clerk,usertask1),rbac_ac(user2_manager,manager,usertask2),rbac_ac(user2_manager,manager,usertask3)]
  Step 6:	 [sc_h_taskExecution_1(user1_manager,manager,usertask2,fnat(n2,0,0),in_usertask2,out_usertask2),sc_h_taskExecution_1(user1_manager,manager,usertask3,fnat(n3,0,0),in_usertask3,out_usertask3),rbac_ac(user1_clerk,clerk,usertask1),rbac_ac(user1_manager,manager,usertask2),rbac_ac(user1_manager,manager,usertask3),rbac_ac(user2_clerk,clerk,usertask1),rbac_ac(user2_manager,manager,usertask2),rbac_ac(user2_manager,manager,usertask3)]
  Step 7:	 [rbac_ac(user1_clerk,clerk,usertask1),rbac_ac(user1_manager,manager,usertask2),rbac_ac(user1_manager,manager,usertask3),rbac_ac(user2_clerk,clerk,usertask1),rbac_ac(user2_manager,manager,usertask2),rbac_ac(user2_manager,manager,usertask3)]
  --------------------------------------------------------------------

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

RESULTS

Attacks Found:			true
Stop Condition Reached:		false
Formula statistics:
  Graph Construction Time:	0.08
  Graph Leveled Off:		no
  Graph2SAT Time (sec):		0.18
  Encoding Time (sec):		0.26
  Depth:			7
  Atoms:			746
  Clauses:			1841
Solving statistics:
  Total Solving Time (sec):	0.0
  Last Solving Time (sec):	0.0
Abstraction/Refinement statistics:
  Validation Time (sec):	0.0
  Models into POPs Time (sec):	0.06
  Refinement iterations:	0

Total Time:	0.32


