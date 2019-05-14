/*
 * Developed by Vibert Bounyasit
 * Last modified 24/02/19 21:55
 *
 * Copyright (c) 2019-present. All right reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.vbounyasit.rift_example.sample

import com.vbounyasit.bigdata.ETL.OptionalJobParameters
import com.vbounyasit.bigdata.EitherRP
import com.vbounyasit.bigdata.transform.ExecutionPlan
import com.vbounyasit.bigdata.transform.TransformOps._
import io.github.vbounyasit.rift_example.sample.data.{SampleApplicationConf, SampleArgument}
import org.apache.spark.sql.SparkSession

case class SampleExecutionPlan(optionalParams: OptionalJobParameters[SampleApplicationConf, SampleArgument])(implicit spark: SparkSession) extends ExecutionPlan {

  override val executionPipelines: SampleExecutionPipelines = new SampleExecutionPipelines(optionalParams)

  import executionPipelines._
  import executionPipelines.transformers._

  override def getExecutionPlan(getSource: String => EitherRP): EitherRP = {
    getSource("source1") ==> pipeline1 ==> DropDuplicates("col1")
  }
}
